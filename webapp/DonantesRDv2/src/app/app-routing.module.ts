import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyAppointmentsComponent } from './_components/my-appointments/my-appointments.component';
import { AdministrationComponent } from './_components/administration/administration.component';
import { BloodBankComponent } from './_components/blood-bank/blood-bank.component';
import { DonateBloodComponent } from './_components/donate-blood/donate-blood.component';
import { DonationAppointmentComponent } from './_components/donation-appointment/donation-appointment.component';
import { DonationCenterComponent } from './_components/donation-center/donation-center.component';
import { HomeComponent } from './_components/home/home.component';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/register/register.component';
import { UserAdministrationComponent } from './_components/user-administration/user-administration.component';
import { AdminGuardService } from './_guards/admin-guard';
import { AuthGuardService } from './_guards/auth-guard';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'signup', component: RegisterComponent},
  {
    path: 'donate-blood', 
    children: [
      {path: '', component: DonateBloodComponent},
      {path: 'appointment', component: DonationAppointmentComponent},
      {path: 'my-appointments', component: MyAppointmentsComponent}
    ]
  },
  {path: 'user-administration', component: UserAdministrationComponent, canActivate: [AuthGuardService, AdminGuardService]},
  {
    path: 'administration',
    // canActivate: [AuthGuardService, AdminGuardService], 
    children: [
      {path: '', component: AdministrationComponent},
      {path: 'blood-bank', component: BloodBankComponent},
      {path: 'user-administration', component: UserAdministrationComponent},
      {path: 'donation-center', component: DonationCenterComponent}
    ]
  },
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
