# Read Dropwizard configuration from Consul's KV store

[Related blog post](http://blog.remmelt.com/2015/06/09/use-consuls-kv-store-for-dropwizard-settings/)

## Example run

Substitute your docker host where it says `192.168.59.103`.


Start a Consul server:
```
docker run -p 8400:8400 -p 8500:8500 -p 8600:53/udp -h node1 gliderlabs/consul-server -bootstrap -ui-dir /ui
```

Create a key:
Visit the Consul ui: [192.168.59.103:8500/ui/#/dc1/kv/](http://192.168.59.103:8500/ui/#/dc1/kv/) and create a key named `settings/someSetting` with any value.

Now clone this repository and run the app:
```
mvn clean package
CONSUL_HOST="192.168.59.103" java -jar target/consul-config-provider.jar server src/main/resources/config.yml
```

The app should build and start up with a warning that I did not provide any health checks.

Let's see if it worked!

```
curl http://localhost:8080/
```

Or just open [localhost:8080](http://localhost:8080/) in your browser.

```
someSetting: set from Consul's KV store
someOtherSetting: from config.yml
someSettingThatDoesNotExist: default_value_for_some_setting
```

Presto.
