package com.example.ezul.api.auth.service.key;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OidcPublicKey {
    String kid;
    String kty;
    String alg;
    String use;
    String n;
    String e;
}
