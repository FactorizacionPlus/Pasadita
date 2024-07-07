export default function getFormattedDateTime(dateString: string): string {
  const date = new Date(dateString);
  const formattedDate = date.toLocaleString("es-MX", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
  const formattedTime = date.toLocaleString("es-MX", {
    hour: "numeric",
    minute: "numeric",
    hour12: false,
  });

  const formattedDateTime = `${formattedDate} - ${formattedTime}hrs`;

  return formattedDateTime;
}
