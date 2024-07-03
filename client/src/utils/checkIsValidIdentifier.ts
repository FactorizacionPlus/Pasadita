import type { identifierType } from "@/types/IdentifierType";

export default function checkIsValidIdentifier(identifier: string, identifierType: identifierType) {
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
