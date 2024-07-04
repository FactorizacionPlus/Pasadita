import type { IdentifierType } from "./IdentifierType";

export default interface User {
  uuid: string;
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: IdentifierType;

  entryCount: number; // Dato extra de utilidad
}
