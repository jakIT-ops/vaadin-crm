package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListViewTest {
    @Autowired
    private ListView listView;

    @Test
    public void formShownWhenContactSelected(){
        Grid<Contact> grid = listView.grid;
        Contact firstContact = getFirstItem(grid);

        ContactForm form = listView.form;

        Assert.assertFalse(from.isVisible());
        grid.asSingleSelect().setValue(firstContact);
        Assert.assertTrue(form.isVisible());
        Assert.assertEquals(firstContact.getFirstName(), form.firstName.getValue());
    }
    private Contact getFirstItem(Grid<Contact> grid) {
        return((ListDataProvider<Contact>) grid.getDataProvider()).getItems().iterator().next();
    }
}
