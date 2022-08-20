package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.tutorial.crm.ui.view.dashboard.DashboardView;
import com.vaadin.tutorial.crm.ui.view.list.ListView;

@CssImport("./styles/shared-styles.css")
@PWA(
        name = "VaadinCRM",
        shortName = "CRM",
        offlineResources = {
                "./styles/offline.css",
                "./images/offline.png"}) // PWA annotation tells Vaadin to create a ServiceWorker and a manifest file. name is the full name of the application for the manifest file.
        // offlineResources is a list of that Vaadin will make available offline through the ServiceWorker.
public class MainLayout extends AppLayout {
    public MainLayout(){

        createHeader();
        createDrawer();
    }

    private void createHeader(){
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");

        // Anchor tag
        Anchor logout = new Anchor("logout", "Log out");


        HorizontalLayout header = new HorizontalLayout(new DrawerToogle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("Header");

        addToNavbar(header);
    }

    private void createDrawer(){
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightCondition.sameLocation());

        addToDrawer(new VerticalLayout(listLink, new RouterLink("Dashboard", DashboardView.class)));
    }
}
