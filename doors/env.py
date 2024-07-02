from typing import List, Tuple

WIFI_CONNECTIONS: List[Tuple[str, str]] = [
    ( "SSID", "PASSWORD" ),
]

SERVER_CONNECTIONS: List[Tuple[str, int]] = [
    ( "LOCALHOST", 8080 ),
    ( "pasadita-production.up.railway.app", 80 ),
]

TERMINAL = b"BARRIER"
