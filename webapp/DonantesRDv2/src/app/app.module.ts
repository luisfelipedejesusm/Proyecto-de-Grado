import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/register/register.component';
import { HomeComponent } from './_components/home/home.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { AdminPanelComponent } from './_components/admin-panel/admin-panel.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { NavbarComponent } from './_components/navbar/navbar.component';
import { MasterheadComponent } from './_components/masterhead/masterhead.component';
import { UserAdministrationComponent } from './_components/user-administration/user-administration.component';
import { AdminGuardService } from './_guards/admin-guard';
import { AuthGuardService } from './_guards/auth-guard';
import { DonateBloodComponent } from './_components/donate-blood/donate-blood.component';
import { AdministrationComponent } from './_components/administration/administration.component';
import { CardComponent } from './_components/card/card.component';
import { DonationCenterComponent } from './_components/donation-center/donation-center.component';
import { DataTablesModule } from 'angular-datatables';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BloodBankComponent } from './_components/blood-bank/blood-bank.component';
import { DonationAppointmentComponent } from './_components/donation-appointment/donation-appointment.component';
import { IConfig, NgxMaskModule } from 'ngx-mask';
import { AlertModule } from '@full-fledged/alerts';
import { MyAppointmentsComponent } from './_components/my-appointments/my-appointments.component';


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
    DonationCenterComponent,
    BloodBankComponent,
    DonationAppointmentComponent,
    MyAppointmentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DataTablesModule,
    NgbModule,
    NgxDatatableModule,
    NgxMaskModule.forRoot(),
    AlertModule.forRoot({maxMessages: 5, timeout: 5000, positionX: 'right'})
  ],
  providers: [authInterceptorProviders, AdminGuardService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
