package edu.tamu.cap.controller;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;
import static edu.tamu.weaver.response.ApiStatus.ERROR;
import static edu.tamu.weaver.validation.model.BusinessValidationType.CREATE;
import static edu.tamu.weaver.validation.model.BusinessValidationType.UPDATE;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static edu.tamu.weaver.validation.model.BusinessValidationType.DELETE;

import org.apache.commons.io.IOUtils;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.GetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.cap.model.IR;
import edu.tamu.cap.model.repo.IRRepo;
import edu.tamu.weaver.response.ApiResponse;
import edu.tamu.weaver.validation.aspect.annotation.WeaverValidatedModel;
import edu.tamu.weaver.validation.aspect.annotation.WeaverValidation;

@RestController
@RequestMapping("/ir")
public class IRController {
	
	@Autowired
	IRRepo irRepo;
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/all")
    @PreAuthorize("hasRole('USER')")
	public ApiResponse allIRs() {
        return new ApiResponse(SUCCESS, irRepo.findAll());
	}
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = CREATE) })
	public ApiResponse createIRs(@RequestBody @WeaverValidatedModel IR ir) {
        logger.info("Creating IR:  " + ir.getName());
        return new ApiResponse(SUCCESS, irRepo.create(ir));
	}
    
    @RequestMapping(value="/update", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = UPDATE) })
	public ApiResponse updateIR(@RequestBody @WeaverValidatedModel IR ir) {
        logger.info("Updating IR:  " + ir.getName());
        return new ApiResponse(SUCCESS,irRepo.update(ir));
	}
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @WeaverValidation(business = { @WeaverValidation.Business(value = DELETE) })
	public ApiResponse deleteIR(@RequestBody @WeaverValidatedModel IR ir) {
        logger.info("Deleating IR:  " + ir.getName());
    	irRepo.delete(ir);
        return new ApiResponse(SUCCESS);
	}
    
    @RequestMapping(value="/test/ping", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
	public ApiResponse testIRPing(@RequestBody @WeaverValidatedModel IR ir) {
    	FcrepoClient client = FcrepoClient.client().build();
    	ApiResponse res = null;
    	
    	try {
			FcrepoResponse response = new GetBuilder(new URI(ir.getUri()), client).perform();
			res = new ApiResponse(SUCCESS, "Pinged with status code: "+response.getStatusCode());
		} catch (FcrepoOperationFailedException | URISyntaxException e) {
			res = new ApiResponse(ERROR, e.getMessage());
		}    	
        return res;
	}
    
    @RequestMapping(value="/test/auth", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
	public ApiResponse testIRAuth(@RequestBody @WeaverValidatedModel IR ir) {
    	FcrepoClient client = FcrepoClient.client().credentials(ir.getUsername(), ir.getPassword()).build();
    	ApiResponse res = null;
    	try {
			FcrepoResponse response = new GetBuilder(new URI(ir.getUri()), client).perform();
			res = new ApiResponse(response.getStatusCode()!=200?ERROR:SUCCESS, "Authentication failed with status code: "+response.getStatusCode());
		} catch (FcrepoOperationFailedException | URISyntaxException e) {
			res = new ApiResponse(ERROR, e.getMessage());
		}    	
    	
        return res;
	}
    
    @RequestMapping(value="/test/content", method=RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
	public ApiResponse testIRContent(@RequestBody @WeaverValidatedModel IR ir) {
    	FcrepoClient client = (ir.getUsername()==null||ir.getPassword()==null)?FcrepoClient.client().build():FcrepoClient.client().credentials(ir.getUsername(), ir.getPassword()).build();
    	ApiResponse res = null;
    	try {
			FcrepoResponse response = new GetBuilder(new URI(ir.getUri()), client).perform();
			String resBody = IOUtils.toString(response.getBody(), "UTF-8");
			res = new ApiResponse(resBody.contains("fedora:RepositoryRoot")?SUCCESS:ERROR, "Get content failed with status code: "+response.getStatusCode());
		} catch (FcrepoOperationFailedException | URISyntaxException | IOException e) {
			res = new ApiResponse(ERROR, e.getMessage());
		}    	
        return res;
	}
    
    @RequestMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
	public ApiResponse getIR(@PathVariable Long id) {
        return new ApiResponse(SUCCESS, irRepo.read(id));
	}

}
