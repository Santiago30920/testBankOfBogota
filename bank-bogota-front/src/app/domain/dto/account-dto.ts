import { EAccount } from '../enums/e-account';


export class AccountDTO {
    id: string;
    customerId: string;
    accountNumber: string;
    status: EAccount;

    constructor(id: string, customerId: string, accountNumber: string, status: EAccount) {
        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.status = status;
    }
}