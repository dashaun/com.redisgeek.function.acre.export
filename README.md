# com.redisgeek.function.acre.export
## Azure Function for ACRE Export

A function to create an export from a give ACRE instance built using:

* [Spring Cloud Function](https://spring.io/projects/spring-cloud-function)
* [Azure SDK for Java](https://github.com/Azure/azure-sdk-for-java)


## | [Getting Started](#getting-started) | [See Also](#see-also) | [Help](#help) | [License](#license) | [Credit](#credit) |

## Getting Started

### Clone the Repository w/ Submodules

To install this example application, run the following commands:
```bash
git clone git@github.com:redisgeek/com.redisgeek.function.acre.export.git
cd com.redisgeek.function.acre.export.git
```

### Update the settings

```bash
cp src/main/azure/
```

Set the environment secrets
```bash
dotnet user-secrets init
dotnet user-secrets set CacheConnection "localhost,abortConnect=false,ssl=false,allowAdmin=false,password="
```

Start the Docker Compose application:
 ```bash
 docker-compose up
 ```

Start the app (in separate shell)
```bash
dotnet run
```

Access the Swashbuckle/Swagger UI:
[https://localhost:5001/swagger/index.html](https://localhost:5001/swagger/index.html)

## See Also

Quick Tutorial on Redis' Powerful Modules:

* [RedisJSON Tutorial](https://developer.redislabs.com/howtos/redisjson)
* [RediSearch Tutorial](https://developer.redislabs.com/howtos/redisearch)

## Help

Please post any questions and comments on the [Redis Discord Server](https://discord.gg/redis),
and remember to visit our [Redis Developer Page](https://developer.redislabs.com) for awesome tutorials,
project and tips.

## License

[MIT Licence](http://www.opensource.org/licenses/mit-license.html)

## Credit

- [DaShaun Carter](https://github.com/dashaun) @ [Redis Labs](https://redislabs.com)
- [Guy Royse](https://github.com/guyroyse) @ [Redis Labs](https://redislabs.com)