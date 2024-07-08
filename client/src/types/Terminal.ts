import type { TerminalType } from "./TerminalType";

export default interface Terminal {
  uuid: string;
  type: TerminalType;
  entryCount: number;
}
