export default function isValidURL(url?: string): boolean {
  try {
    new URL(url || "");
    return true;
  } catch (ex) {
    return false;
  }
}
