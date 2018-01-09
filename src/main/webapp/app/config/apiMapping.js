// CONVENTION: must match model name, case sensitive
var apiMapping = {
  User: {
    channel: '/channel/user',
    instantiate: {
        'endpoint': '/private/queue',
        'controller': 'user',
        'method': 'credentials'
    },
    all: {
        'endpoint': '/private/queue',
        'controller': 'user',
        'method': 'all'
    },
    update: {
        'endpoint': '/private/queue',
        'controller': 'user',
        'method': 'update'
    },
    remove: {
        'endpoint': '/private/queue',
        'controller': 'user',
        'method': 'delete'
    }
  },
  IRContext: {
    channel: '/channel/ir-context',
    validations: false,
    lazy: true,
    load: {
      'endpoint': '/private/queue',
      'controller': 'ir-context',
      'method': ':type/:irid'
    },
    container: {
      'endpoint': '/private/queue',
      'controller': 'ir-context',
      'method': ':type/:irid/container'
    },
    resource: {
      'endpoint': '/private/queue',
      'controller': 'ir-context',
      'method': ':type/:irid/resource'
    }
  },
  TestIRSettings: {
    testPing: {
      'endpoint': '/private/queue',
      'controller': 'ir-test',
      'method': ':type/ping'
    },
    testAuth: {
        'endpoint': '/private/queue',
        'controller': 'ir-test',
        'method': ':type/auth'
    },
    testContent: {
        'endpoint': '/private/queue',
        'controller': 'ir-test',
        'method': ':type/content'
    }
  },
  IR: {
    channel: '/channel/ir',
    validations: true,
    all: {
        'endpoint': '/private/queue',
        'controller': 'ir',
        'method': 'all'
    },
    create: {
      'endpoint': '/private/queue',
      'controller': 'ir',
      'method': 'create'
    },
    update: {
        'endpoint': '/private/queue',
        'controller': 'ir',
        'method': 'update'
    },
    remove: {
        'endpoint': '/private/queue',
        'controller': 'ir',
        'method': 'delete'
    },
    getTypes: {
      'endpoint': '/private/queue',
      'controller': 'ir',
      'method': 'types'
    }
  }
};