export default interface AccessRequest {
  residentId: string;
  residentName: string;
  startDate: Date;
  endDate: Date;
  status: "ACCEPTED" | "REJECTED" | "PENDING";
  image: string;
}
