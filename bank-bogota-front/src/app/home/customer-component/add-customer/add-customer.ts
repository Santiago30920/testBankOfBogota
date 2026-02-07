import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { CustomerDTO } from '../../../domain/dto/customer-dto';
import { DocumentType } from '../../../domain/enums/document-type.enum';

@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule
  ],
  templateUrl: './add-customer.html',
  styleUrl: './add-customer.css',
})
export class AddCustomer implements OnInit {
  customerForm!: FormGroup;
  documentTypes = Object.values(DocumentType);

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddCustomer>
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.customerForm = this.fb.group({
      documentType: [DocumentType.CC, Validators.required],
      documentNumber: ['', [Validators.required, Validators.pattern(/^\d{6,15}$/)]],
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const formValue = this.customerForm.value;
      const customer = new CustomerDTO(
        '',
        formValue.documentType,
        formValue.documentNumber,
        formValue.fullName,
        formValue.email
      );
      this.dialogRef.close(customer);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  getDocumentTypeLabel(type: DocumentType): string {
    const labels: Record<DocumentType, string> = {
      [DocumentType.CC]: 'Cédula de Ciudadanía',
      [DocumentType.CE]: 'Cédula de Extranjería',
      [DocumentType.NIT]: 'NIT',
      [DocumentType.TI]: 'Tarjeta de Identidad',
      [DocumentType.PASAPORTE]: 'Pasaporte'
    };
    return labels[type];
  }
}
