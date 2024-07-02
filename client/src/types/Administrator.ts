import type { StatusType } from "./StatusType";

export default interface User {
    uuid: string,
    identifier: string;
    firstName: string;
    lastName: string;
    imageUrl: string;
    status: StatusType;
    identifierType: "DUI" | "PASSPORT";
  }
  