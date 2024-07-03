import type { identifierType } from "./IdentifierType";

export default interface User {
  uuid: string,
  identifier: string;
  firstName: string;
  lastName: string;
  identifierType: identifierType;
}
