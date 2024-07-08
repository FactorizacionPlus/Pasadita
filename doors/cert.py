import ssl

context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
context.load_cert_chain("certs/ec_cert.der", "certs/ec_key.der")
