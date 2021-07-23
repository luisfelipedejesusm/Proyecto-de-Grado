import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { BloodBank } from 'src/app/_models/blood-bank.model';
import { AdministrationService } from 'src/app/_services/administration.service';

@Component({
  selector: 'app-blood-bank',
  templateUrl: './blood-bank.component.html',
  styleUrls: ['./blood-bank.component.css']
})
export class BloodBankComponent implements OnInit, OnDestroy {

  constructor(private modalService: NgbModal, private administrationService: AdministrationService) { }

  bloodbanks: BloodBank[] = []
  subscriptions: Subscription = new Subscription();

  model: BloodBank = new BloodBank();

  ngOnInit(): void {
    this.subscriptions.add(
      this.administrationService.getBloodBanks().subscribe(response => {
        this.bloodbanks = response;
      })
    );
  }

  remove(bloodbank: BloodBank){
    this.bloodbanks.forEach((item, idx) => {
      if(item.id == bloodbank.id) this.bloodbanks.splice(idx, 1);
    });
    this.rerender();
    this.subscriptions.add(
      this.administrationService.deleteBloodBank(bloodbank).subscribe()
    )
  }

  open(modal: any){
    this.model = new BloodBank();
    this.modalService.open(modal);
  }

  clear(modal: any){
    modal.close();
  }

  newBloodBank(){
    if(this.model.id){
      this.subscriptions.add(
        this.administrationService.updateBloodBank(this.model).subscribe()
      )
    }else{
      this.subscriptions.add(
        this.administrationService.createBloodBank(this.model).subscribe(response => {
          this.model.id = response.id;
          this.bloodbanks.push(this.model);
          this.rerender();
        })
      );
    }
    this.modalService.dismissAll();
  }

  update(bloodbank: BloodBank, modal: any){
    this.model = bloodbank;
    this.modalService.open(modal);
  }

  rerender(){
    this.bloodbanks = [...this.bloodbanks];
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

}
