import { NgModule } from "@angular/core"
import { FormsModule, ReactiveFormsModule } from "@angular/forms"
import { HttpClientModule } from "@angular/common/http"
import { CommonModule } from "@angular/common"
import { AppComponent } from "../app.component"
import { BrowserModule } from "@angular/platform-browser"
import { Ng2SearchPipeModule } from "ng2-search-filter"

@NgModule({
declarations: [
  
  ],
  imports: [FormsModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  
  providers: [],
})

export class AppModule{}