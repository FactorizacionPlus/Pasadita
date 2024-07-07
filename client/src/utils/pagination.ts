import type Pagination from "@/types/utils/Pagination";

export function buildPaginationQuery(pagination: Partial<Pagination>) {
  const params = new URLSearchParams();
  if (pagination.size) {
    params.set("size", pagination.size.toString());
  }
  if (pagination.page) {
    params.set("page", pagination.page.toString());
  }
  return params;
}

export function paginatedEndpoint(endpoint: string, pagination: Partial<Pagination>): string {
  const params = buildPaginationQuery(pagination);
  if (params.size == 0) {
    return endpoint;
  }
  return `${endpoint}?${params.toString()}`;
}
