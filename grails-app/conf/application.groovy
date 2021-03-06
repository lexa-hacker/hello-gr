grails {
	profile = 'web'
	codegen {
		defaultPackage = 'hellogr'
	}
	spring {
		transactionManagement {
			proxies = false
		}
	}
	gorm {
		// Whether to autowire entities.
		// Disabled by default for performance reasons.
		autowire = false
		reactor {
			// Whether to translate GORM events into Reactor events
			// Disabled by default for performance reasons
			events = false
		}
	}
	mime {
		disable {
			accept {
				header {
					userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
				}
			}
		}
		types {
			all = '*/*'
			atom = 'application/atom+xml'
			css = 'text/css'
			csv = 'text/csv'
			form = 'application/x-www-form-urlencoded'
			html = ['text/html', 'application/xhtml+xml']
			js = 'text/javascript'
			json = ['application/json', 'text/json']
			multipartForm = 'multipart/form-data'
			pdf = 'application/pdf'
			rss = 'application/rss+xml'
			text = 'text/plain'
			hal = ['application/hal+json', 'application/hal+xml']
			xml = ['text/xml', 'application/xml']
		}
	}
	urlmapping {
		cache {
			maxsize = 1000
		}
	}
	controllers {
		defaultScope = 'singleton'
	}
	converters {
		encoding = 'UTF-8'
	}
	views {
		'default' {
			codec = 'html'
		}
		gsp {
			encoding = 'UTF-8'
			htmlcodec = 'xml'
			codecs {
				expression = 'html'
				scriptlets = 'html'
				taglib = 'none'
				staticparts = 'none'
			}
		}
	}
}

info {
	app {
		name = '@info.app.name@'
		version = '@info.app.version@'
		grailsVersion = '@info.app.grailsVersion@'
	}
}

spring {
	main {
		setProperty 'banner-mode', 'off'
	}
	groovy {
		template {
			setProperty 'check-template-location', false
		}
	}
}

// Spring Actuator Endpoints are Disabled by Default
endpoints {
	enabled = false
	jmx {
		enabled = true
		setProperty 'unique-names', true
	}
}

hibernate {
	cache {
		queries = false
		use_second_level_cache = true
		use_query_cache = false
		region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
	}
}

dataSource {
	pooled = true
	jmxExport = true
	driverClassName = 'org.postgresql.Driver'
	dialect = 'org.hibernate.dialect.PostgreSQLDialect'
	username = 'postgres'
	password = '1qaz!QAZ'
}

environments {
	development {
		dataSource {
			dbCreate = 'none'
			//url: jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
			url = 'jdbc:postgresql://localhost:5433/company?currentSchema=public'
		}
	}
	test {
		dataSource {
			dbCreate = 'update'
			url = 'jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
		}
	}
	production {
		dataSource {
			dbCreate = 'none'
			url = 'jdbc:h2:./prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
			properties {
				jmxEnabled = true
				initialSize = 5
				maxActive = 50
				minIdle = 5
				maxIdle = 25
				maxWait = 10000
				maxAge = 600000
				timeBetweenEvictionRunsMillis = 5000
				minEvictableIdleTimeMillis = 60000
				validationQuery = 'SELECT 1'
				validationQueryTimeout = 3
				validationInterval = 15000
				testOnBorrow = true
				testWhileIdle = true
				testOnReturn = false
				jdbcInterceptors = 'ConnectionState'
				defaultTransactionIsolation = 2 // TRANSACTION_READ_COMMITTED
			}
		}
	}
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'hellogr.Person'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'hellogr.PersonRole'
grails.plugin.springsecurity.authority.className = 'hellogr.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',             access: ['permitAll']],
	[pattern: '/**/images/**',          access: ['permitAll']],
	[pattern: '/**/favicon.ico',        access: ['permitAll']],
	[pattern: '/person/**',            access: ['ROLE_ADMIN', 'ROLE_USER']],
    [pattern: '/person/createPerson',     access: ['permitAll']],
	[pattern: '/api/user/create',     access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

