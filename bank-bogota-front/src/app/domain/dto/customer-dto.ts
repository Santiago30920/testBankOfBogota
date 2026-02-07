import { DocumentType } from '../enums/document-type.enum';

export class CustomerDTO {
    id: string;
    documentType: DocumentType;
    documentNumber: string;
    fullName: string;
    email: string;

    constructor(
        id: string,
        documentType: DocumentType,
        documentNumber: string,
        fullName: string,
        email: string
    ) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.email = email;
    }
}
