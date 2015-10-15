package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Editor;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @editor Riad YOUSFI
 */
@WebServlet(name = "EditorController", urlPatterns = {"/Admin/Editor"})
public class EditorController extends HttpServlet {

    private final String editorForm = "/WEB-INF/admin/EditorForm.jsp";
    private final String editorIndex = "/WEB-INF/admin/EditorIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "add":
                request.getRequestDispatcher(editorForm).forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    //FindById Editor from database
                    Editor editor = new Editor(id, "Eni");
                    request.setAttribute("editor", editor);
                    request.getRequestDispatcher(editorForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "L'editeur que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Editor from database
                request.setAttribute("alert", new Alert("Editeur supprimé avec succes"));
                goIndex(request, response);
                break;
            case "index":
                goIndex(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String name = request.getParameter("name").trim();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Editor editor = new Editor();

        try {
            Validator.required(name);
            editor.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("editor", editor);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(editorForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Editor from database
                request.setAttribute("alert", new Alert("Editeur modifié avec succes"));
            } else {
                //Insert Editor from database
                request.setAttribute("alert", new Alert("Editeur ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Editor> editors = findAllEditors();
        request.setAttribute("editors", editors);
        request.getRequestDispatcher(editorIndex).forward(request, response);
    }

    private List<Editor> findAllEditors() {
        //FindAll Editor from database
        List<Editor> editors = new ArrayList<>();
        editors.add(new Editor(1, "Eni"));
        editors.add(new Editor(2, "OpenClassrooms - ex-Site du Zéro"));
        editors.add(new Editor(3, "Eyrolles"));
        return editors;
    }

}
