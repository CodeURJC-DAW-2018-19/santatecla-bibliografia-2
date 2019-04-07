/**
 * The main component that renders single TabComponent
 * instances.
 */
import {
    Component,
    ContentChildren,
    QueryList,
    AfterContentInit,
    ViewChild,
    ComponentFactoryResolver,
    ViewContainerRef
  } from '@angular/core';
  
  import { TabComponent } from './tab.component';
  import { DynamicTabsDirective } from './dynamic-tabs.directive';
  
  @Component({
    selector: 'my-tabs',
    template: `
      <ul class="nav nav-tabs">
        <li *ngFor="let tab of tabs" (click)="selectTab(tab)" [class.active]="tab.active">
          <a href="#">{{tab.title}}</a>
        </li>
        <!-- dynamic tabs -->
        <li *ngFor="let tab of dynamicTabs" (click)="selectTab(tab)" [class.active]="tab.active">
          <a href="#">{{tab.title}} <span class="tab-close" *ngIf="tab.isCloseable" (click)="closeTab(tab)">x</span></a>
        </li>
      </ul>
      <ng-content></ng-content>
      <ng-template dynamic-tabs #container></ng-template>
    `,
    styles: [
      `
      .tab-close {
        color: gray;
        text-align: right;
        cursor: pointer;
      }
      `
    ]
  })
  export class TabsComponent implements AfterContentInit {
    dynamicTabs: TabComponent[] = [];
  
    @ContentChildren(TabComponent) tabs: QueryList<TabComponent>;
  
    @ViewChild(DynamicTabsDirective) dynamicTabPlaceholder: DynamicTabsDirective;
  
    /*
      Alternative approach of using an anchor directive
      would be to simply get hold of a template variable
      as follows
    */
    // @ViewChild('container', {read: ViewContainerRef}) dynamicTabPlaceholder;
  
    constructor(private _componentFactoryResolver: ComponentFactoryResolver) {}
  
    // contentChildren are set
    ngAfterContentInit() {
      // get all active tabs
      const activeTabs = this.tabs.filter(tab => tab.active);
  
      // if there is no active tab set, activate the first
      if (activeTabs.length === 0) {
        this.selectTab(this.tabs.first);
      }
    }
  
    openTab(title: string, data, isCloseable = false) {
      // get a component factory for our TabComponent
      const componentFactory = this._componentFactoryResolver.resolveComponentFactory(
        TabComponent
      );
  
      // fetch the view container reference from our anchor directive
      const viewContainerRef = this.dynamicTabPlaceholder.viewContainer;
  
      // alternatively...
      // let viewContainerRef = this.dynamicTabPlaceholder;
  
      // create a component instance
      const componentRef = viewContainerRef.createComponent(componentFactory);
  
      // set the according properties on our component instance
      const instance: TabComponent = componentRef.instance as TabComponent;
      instance.title = title;
      instance.dataContext = data;
      instance.isCloseable = isCloseable;
  
      // remember the dynamic component for rendering the
      // tab navigation headers
      this.dynamicTabs.push(componentRef.instance as TabComponent);
  
      // set it active
      this.selectTab(this.dynamicTabs[this.dynamicTabs.length - 1]);
    }
  
    selectTab(tab: TabComponent) {
      // deactivate all tabs
      this.tabs.toArray().forEach(tab => (tab.active = false));
      this.dynamicTabs.forEach(tab => (tab.active = false));
  
      // activate the tab the user has clicked on.
      tab.active = true;
    }
  
    closeTab(tab: TabComponent) {
      for (let i = 0; i < this.dynamicTabs.length; i++) {
        if (this.dynamicTabs[i] === tab) {
          // remove the tab from our array
          this.dynamicTabs.splice(i, 1);
  
          // destroy our dynamically created component again
          let viewContainerRef = this.dynamicTabPlaceholder.viewContainer;
          // let viewContainerRef = this.dynamicTabPlaceholder;
          viewContainerRef.remove(i);
  
          // set tab index to 1st one
          this.selectTab(this.tabs.first);
          break;
        }
      }
    }
  
    closeActiveTab() {
      const activeTabs = this.dynamicTabs.filter(tab => tab.active);
      if (activeTabs.length > 0) {
        // close the 1st active tab (should only be one at a time)
        this.closeTab(activeTabs[0]);
      }
    }
  }
  