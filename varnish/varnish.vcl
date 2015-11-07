vcl 4.0;

backend zuul {
  .host = "zuul";
  .port = "8080";
}

backend keycloak {
    .host = "keycloak";
    .port = "8080";
}

sub vcl_recv {
    if (req.url ~ "^/auth/") {
        set req.backend_hint = keycloak;
    } else {
        set req.backend_hint = zuul;
    }
}

sub vcl_backend_response {
    if (bereq.url ~ "^/auth/") {
        set beresp.ttl = 0s;
    } else {
        set beresp.ttl = 10s;
    }

}