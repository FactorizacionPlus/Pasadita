from typing import List, Tuple

WIFI_CONNECTIONS: List[Tuple[str, str]] = [
    ( "SSID", "PASSWORD" ),
]

SERVER_CONNECTIONS: List[Tuple[str, int]] = [
    ( "LOCALHOST", 8080 ),
    ( "pasadita-production.up.railway.app", 80 ),
]

# Terminal UUID
# TODO: Maybe try to get this UUID from the server?
TERMINAL = b"1c6586ba-32a1-442b-a74c-8d2c32306a65"
