import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { MessageService } from 'primeng/api';
import { CustomerService } from '../../domain/service/customer-service';
import { CustomerDTO } from '../../domain/dto/customer-dto';
import { DocumentType } from '../../domain/enums/document-type.enum';
import { AddCustomer } from './add-customer/add-customer';

@Component({
  selector: 'app-customer-component',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    ButtonModule,
    ToastModule,
    InputTextModule,
    TooltipModule,
    MatDialogModule
  ],
  providers: [MessageService],
  templateUrl: './customer-component.html',
  styleUrl: './customer-component.css',
})
export class CustomerComponent implements OnInit {
  customers: CustomerDTO[] = [];
  loading: boolean = false;

  constructor(
    private customerService: CustomerService,
    private messageService: MessageService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.loading = true;
    this.customerService.getCustomers().subscribe({
      next: (response) => {
        this.customers = response.data;
        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error al Cargar',
          detail: error.message,
          life: 5000
        });
      }
    });
  }

  openAddCustomerModal(): void {
    const dialogRef = this.dialog.open(AddCustomer, {
      width: '600px',
      disableClose: false,
      panelClass: 'custom-dialog-container'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.createCustomer(result);
      }
    });
  }

  createCustomer(customerData: CustomerDTO): void {
    this.loading = true;
    this.customerService.createCustomer(customerData).subscribe({
      next: (response) => {
        this.loading = false;
        this.messageService.add({
          severity: 'success',
          summary: 'Cliente Registrado',
          detail: response.message,
          life: 3000
        });
        this.loadCustomers();
      },
      error: (error) => {
        this.loading = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error al Registrar',
          detail: error.message,
          life: 5000
        });
      }
    });
  }

  getDocumentTypeLabel(type: DocumentType): string {
    const labels: Record<DocumentType, string> = {
      [DocumentType.CC]: 'Cédula de Ciudadanía',
      [DocumentType.CE]: 'Cédula de Extranjería',
      [DocumentType.NIT]: 'NIT',
      [DocumentType.TI]: 'Tarjeta de Identidad',
      [DocumentType.PASAPORTE]: 'Pasaporte'
    };
    return labels[type] || type;
  }
}
