export class Error {
  message: string
}

export class ResponseData<T> {
  success: boolean;
  data: T;
  errors: Error[];
}
