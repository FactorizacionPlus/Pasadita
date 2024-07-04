import type { IdentifierType } from "@/types/User/IdentifierType";

export default function checkIsValidIdentifier(identifier: string, identifierType: IdentifierType) {
  let regex: RegExp;

  switch (identifierType) {
    case "PASSPORT":
      regex = /^[A-Z][0-9]{6}$/;
      return regex.test(identifier);
    case "DUI":
      regex = /^[0-9]{8}-[0-9]$/;
      return regex.test(identifier);
  }
}
