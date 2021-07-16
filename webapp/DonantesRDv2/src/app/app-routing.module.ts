import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdministrationComponent } from './administration/administration.component';
import { DonateBloodComponent } from './donate-blood/donate-blood.component';
import { DonationCenterComponent } from './donation-center/donation-center.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserAdministrationComponent } from './user-administration/user-administration.component';
import { AdminGuardService } from './_guards/admin-guard';
import { AuthGuardService } from './_guards/auth-guard';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'signup', component: RegisterComponent},
  {path: 'donate-blood', component: DonateBloodComponent},
  {path: 'user-administration', component: UserAdministrationComponent, canActivate: [AuthGuardService, AdminGuardService]},
  {
    path: 'administration',
    // canActivate: [AuthGuardService, AdminGuardService], 
    children: [
      {path: '', component: AdministrationComponent},
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
