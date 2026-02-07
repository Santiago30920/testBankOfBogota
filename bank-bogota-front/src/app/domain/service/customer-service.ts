import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponseDTO } from '../dto/api-response-dto';
import { CustomerDTO } from '../dto/customer-dto';
import { ECustomer } from '../enums/e-customer';
import { UtilitieService } from './utilitie-service';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
      private readonly apiUrl: string = `${environment.host}`+ECustomer.API_CUSTOMER;

    constructor(private http: HttpClient, private utilitieService: UtilitieService) {}

    createCustomer(customer: CustomerDTO): Observable<ApiResponseDTO<CustomerDTO>> {
        return this.http.post<ApiResponseDTO<CustomerDTO>>(this.apiUrl, customer)
            .pipe(catchError(this.utilitieService.handleError));
    }

    getCustomers(): Observable<ApiResponseDTO<CustomerDTO>> {
        return this.http.get<ApiResponseDTO<CustomerDTO>>(this.apiUrl)
            .pipe(catchError(this.utilitieService.handleError));
    }
}
