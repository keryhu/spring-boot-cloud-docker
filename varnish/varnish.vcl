vcl 4.0;

backend default {
  .host = "192.168.99.100";
  .port = "7080";
}


sub vcl_backend_response {
    if (bereq.url ~ "^/api") {
        set beresp.ttl = 0s;
    } else {
        set beresp.ttl = 10s;
    }
}
