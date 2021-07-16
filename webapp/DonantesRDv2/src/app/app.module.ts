import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { NavbarComponent } from './navbar/navbar.component';
import { MasterheadComponent } from './masterhead/masterhead.component';
import { UserAdministrationComponent } from './user-administration/user-administration.component';
import { AdminGuardService } from './_guards/admin-guard';
import { AuthGuardService } from './_guards/auth-guard';
import { DonateBloodComponent } from './donate-blood/donate-blood.component';
import { AdministrationComponent } from './administration/administration.component';
import { CardComponent } from './card/card.component';
import { DonationCenterComponent } from './donation-center/donation-center.component';
import { DataTablesModule } from 'angular-datatables';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    AdminPanelComponent,
    NavbarComponent,
    MasterheadComponent,
    UserAdministrationComponent,
    DonateBloodComponent,
    AdministrationComponent,
    CardComponent,
    DonationCenterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DataTablesModule
  ],
  providers: [authInterceptorProviders, AdminGuardService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
