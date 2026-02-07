import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { RippleModule } from 'primeng/ripple';
import { DrawerModule } from 'primeng/drawer';
import { BadgeModule } from 'primeng/badge';
import { MenuItem } from 'primeng/api';
import { RouterOutlet, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    ButtonModule,
    DrawerModule,
    MenuModule,
    RippleModule,
    BadgeModule,
    RouterOutlet
  ],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  drawerVisible: boolean = false;
  sidebarExpanded: boolean = false;
  activeSection: string = 'clientes';
  isMobile: boolean = false;

  menuItems: MenuItem[] = [
    {
      label: 'Clientes',
      icon: 'pi pi-users',
      command: () => this.selectSection('customer')
    },
    {
      label: 'Cuentas',
      icon: 'pi pi-wallet',
      command: () => this.selectSection('account')
    }
  ];

  constructor(private router: Router) {
    this.checkScreenSize();
    this.selectSection('customer');
    window.addEventListener('resize', () => this.checkScreenSize());
  }

  checkScreenSize(): void {
    this.isMobile = window.innerWidth < 768;
    if (this.isMobile) {
      this.sidebarExpanded = false;
    }
  }

  selectSection(section: string): void {
    this.activeSection = section;
    this.router.navigate([`/${section}`]);
    if (this.isMobile) {
      this.drawerVisible = false;
    }
  }

  toggleSidebar(): void {
    if (this.isMobile) {
      this.drawerVisible = !this.drawerVisible;
    } else {
      this.sidebarExpanded = !this.sidebarExpanded;
    }
  }

  toggleDrawer(): void {
    this.drawerVisible = !this.drawerVisible;
  }
}
