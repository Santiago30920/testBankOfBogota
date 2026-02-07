import { Routes } from '@angular/router';
import { Home } from './home/home';
import { CustomerComponent } from './home/customer-component/customer-component';
import { AccountComponent } from './home/account-component/account-component';

export const routes: Routes = [
    { path: '', component: Home , children: [
        {path: 'customer', component: CustomerComponent},
        {path: 'account', component: AccountComponent}
    ]},
];
