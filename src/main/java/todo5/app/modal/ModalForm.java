/**
 * @(#)ModalForm.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.app.modal;

import java.io.Serializable;

import lombok.Data;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
@Data
public class ModalForm implements Serializable{

    private static final long serialVersionUID = 1L;

    private String todoId;
    private String todoCategory;
    private String todoTitle;
    private String firstName;
    private String lastName;
    private String todoPriority;
    private String createDate;
    private String expiryDate;

}
