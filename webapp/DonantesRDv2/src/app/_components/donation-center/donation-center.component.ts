import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormGroupDirective } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DataTableDirective } from 'angular-datatables';
import { Subject, Subscription } from 'rxjs';
import { DonationCenter } from '../../_models/donation-center.model';
import { AdministrationService } from '../../_services/administration.service';

@Component({
  selector: 'app-donation-center',
  templateUrl: './donation-center.component.html',
  styleUrls: ['./donation-center.component.css']
})
export class DonationCenterComponent implements OnInit, OnDestroy{
  constructor(private modalService: NgbModal, private administrationService: AdministrationService) {}

  model = new DonationCenter(); 
  donationCenters: DonationCenter[] = [];

  subscriptions: Subscription = new Subscription();

  ngOnInit(): void {
    this.subscriptions.add( 
      this.administrationService.getDonationCenters().subscribe(response => {
        this.donationCenters = response;
      })
    );
  }

  rerender(): void {
    this.donationCenters = [...this.donationCenters];
  }

   ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  open(content: any){
    this.modalService.open(content);
    this.model = new DonationCenter();
  }

  newDonationCenter(){
    
      if(this.model.id){
        this.subscriptions.add(
          this.administrationService.updateDonationCenter(this.model).subscribe()
        )
      }else{
        this.subscriptions.add(
          this.administrationService.createDonationCenter(this.model).subscribe(response => {
            this.model.id = response.id;
            this.donationCenters.push(this.model);
            this.rerender();
          })
        );
      }
      this.modalService.dismissAll();
  }

  update(center: DonationCenter, modal: any){
    this.model = center;
    this.modalService.open(modal);
  }

  remove(center: DonationCenter){
    this.donationCenters.forEach((item, idx) => {
      if(item.id == center.id) this.donationCenters.splice(idx, 1);
    });
    this.subscriptions.add(
      this.administrationService.deleteDonationCenter(center).subscribe()
    )
    this.rerender();
  }

  clear(modal: any){
    modal.close();
  }
}
