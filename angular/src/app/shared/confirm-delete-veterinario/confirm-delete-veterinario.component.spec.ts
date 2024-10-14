import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteVeterinarioComponent } from './confirm-delete-veterinario.component';

describe('ConfirmDeleteVeterinarioComponent', () => {
  let component: ConfirmDeleteVeterinarioComponent;
  let fixture: ComponentFixture<ConfirmDeleteVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteVeterinarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmDeleteVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
