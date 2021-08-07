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
import { AdminGuardService } from './_guards/admin.guard';
import { AuthGuardService } from './_guards/auth.guard';
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
import { UnAuthGuardService } from './_guards/unauth.guard';
import { EditProfileComponent } from './_components/edit-profile/edit-profile.component';
import { AgmCoreModule } from '@agm/core';
import { DonationCenterModalComponent } from './_components/_modals/donation-center-modal/donation-center-modal.component';
import { UserGuardService } from './_guards/user.guard';
import { DonationCenterGuardService } from './_guards/donation-center.guard';
import { NewCampaignComponent } from './_components/new-campaign/new-campaign.component';
import { AngularFireStorageModule } from '@angular/fire/storage';
import { AngularFireModule } from '@angular/fire';
import { environment } from "../environments/environment";
import { CampaingsComponent } from './_components/campaings/campaings.component';




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
    MyAppointmentsComponent,
    EditProfileComponent,
    DonationCenterModalComponent,
    NewCampaignComponent,
    CampaingsComponent
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
    AlertModule.forRoot({maxMessages: 5, timeout: 5000, positionX: 'right'}),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDAF5F-zRJCQJyEM6SN6Ary9a2nYyarZrw',
      libraries: ['places']
    }),
    AngularFireStorageModule,
    AngularFireModule.initializeApp(environment.firebaseConfig, "cloud")
  ],
  providers: [authInterceptorProviders, AdminGuardService, AuthGuardService, UnAuthGuardService, UserGuardService, DonationCenterGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { } 
