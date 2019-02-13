# Contributing To CAP

Though CAP is developed and maintained by Texas A&M University Libraries, we welcome comunity contributions. Invovlment in CAP can take many forms:

#### Using CAP: 

Deploying cap and trying it out at your own instituion is itself a way of contribuitng to the development process. For more information on deployment strategies please see the [relavant documentation](https://github.com/TAMULib/Cap/blob/master/README.md).
  
#### Filing Issues: 

Once you are using CAP the creation of new issues through Github is a major method of contribution towards CAP development. Issues can be motivated by the discovery of a bug in the software, or by the desire to see either new features added to cap or existing features behaving differently.

These three catagories of issue can be submitted using the following template:

``` 
[*] Bug Report
[] Feature Enhancement
[] Feature Request

Current Behavior:

[A description of the current behavior. This could include a list of steps to reproduce in the instance of a 
Bug Report, and can be 'N/A' for Feature Requests]

Desired Behavior:

[A description the expected/desired behavior of the application]


Relavant Information:

[This section can include relavant error messages, or links to information that pertains to the issue]

````

Once an issue has been filed it will be currated by a CAP developer and recieve the appropriate labeling.

#### Creating Pull Requests:

Comunity code and documentation contributions are welcome, and should take the form of a Github Pull Request (PR). Each PR will need to be reviewed by a CAP developer. A review will result in the PR being accepted and merged, a descriptive request for changes, or the PR being closed along with a detailed explaination.

It is our intention to maintain labeling on issues that are deamed to be low difficulty, in order to provide a good point of entry for those looking to begin contributing code or documentation.

A PR description should include a list of the specific issues resolved, the predicted semantic versioning impact of the changes and a description which charachterizes the nature of the changes made.

For more information about semantic versioning please see [this reference](https://semver.org/). In general keep in mind:

- A Major Change is a breaking change that is not backwards compatable.
- A Minor Change is a non breaking change that is backwards compatable to the last major change.
- A Patch is a trivial change or bug fix that should not impact compatability

Please conform you PR description to the following template:

```
Resolves:

- [List of # references to specific issues addressed by this PR]

Changes:

[*] Major
[] Minor
[] Patch

[A description of the approach taken to resolving those issues]

```

In addition to a well formated PR we would also appreciate granular commits with descriptive messages. For instance a good commit message may be `Moved /foo endpoint logic into a FooService`, whereas `Everything I did Friday` is less granular or descriptive.

If your commits do not conform to these recomendation you might consider rebasing your PR. Instructions for how this might work can be found [here](https://help.github.com/articles/about-git-rebase/).


#### Good Luck!

We look forward to seeing your contributions. If you have any additional questions please contact the CAP developers at [helpdesk@library.tamu.edu]().