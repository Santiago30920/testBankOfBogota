export class ApiResponseDTO<T> {
    total: number;
    data: T[];
    message: string;
    status: number;

    constructor(total: number, data: T[], message: string, status: number) {
        this.total = total;
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
