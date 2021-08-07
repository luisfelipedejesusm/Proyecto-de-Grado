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
import { AdminGuardService } from './_guards/admin.guard';
import { AuthGuardService } from './_guards/auth.guard';
import { UnAuthGuardService } from './_guards/unauth.guard';
import { ProfileComponent } from './_components/profile/profile.component';
import { EditProfileComponent } from './_components/edit-profile/edit-profile.component';
import { UserGuardService } from './_guards/user.guard';
import { DonationCenterGuardService } from './_guards/donation-center.guard';
import { NewCampaignComponent } from './_components/new-campaign/new-campaign.component';
import { CampaingsComponent } from './_components/campaings/campaings.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [UnAuthGuardService]},
  {path: 'home', component: HomeComponent},
  {path: 'signup', component: RegisterComponent, canActivate: [UnAuthGuardService]},
  {
    path: 'profile', 
    canActivate: [AuthGuardService, UserGuardService],
    children: [
      {path: '', component: ProfileComponent},
      {path: 'edit', component: EditProfileComponent}
    ]
  },
  {
    path: 'donate-blood', 
    children: [
      {path: '', component: DonateBloodComponent, canActivate: [AuthGuardService]},
      {path: 'appointment', component: DonationAppointmentComponent},
      {path: 'my-appointments', component: MyAppointmentsComponent, canActivate: [AuthGuardService] }
    ]
  },
  {path: 'user-administration', component: UserAdministrationComponent, canActivate: [AuthGuardService, AdminGuardService]},
  {
    path: 'administration',
    canActivate: [AuthGuardService, AdminGuardService], 
    children: [
      {path: '', component: AdministrationComponent},
      {path: 'blood-bank', component: BloodBankComponent},
      {path: 'user-administration', component: UserAdministrationComponent},
      {
        path: 'donation-center', component: DonationCenterComponent}
    ]
  },
  {
    path: "donation-center",
    canActivate: [AuthGuardService, DonationCenterGuardService],
    children: [
      {path: "new-campaign", component: NewCampaignComponent},
      {path: "campaigns", component: CampaingsComponent}
    ]
  },
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'top'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
