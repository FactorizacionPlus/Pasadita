import type { Identifier } from "./IdentifierType";
import type { StatusType } from "./StatusType";

export default interface Invited {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: Identifier;
  email: string;
  imageUrl: string;
  status: StatusType;
}
