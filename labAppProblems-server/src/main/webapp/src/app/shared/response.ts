export class ResponseData<T> {
  success: boolean;
  data: T;
  errors: Array<Error>;
}
