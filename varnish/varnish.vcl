vcl 4.0;

backend keycloak_proxy {
  .host = "keycloak_proxy";
  .port = "7080";
}

sub vcl_recv {
        set req.backend_hint = keycloak_proxy;
}

sub vcl_backend_response {
    if (bereq.url ~ "^/api") {
        set beresp.ttl = 0s;
    } else {
        set beresp.ttl = 10s;
    }
}
