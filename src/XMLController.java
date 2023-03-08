import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XMLController 
{
    private String path;

    public XMLController(String targetPath)
    {
        path = targetPath;
    }
    // returns the name of the targeted element
    public String readElementName(String targetElementName) 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try 
        {
            // create a new DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // parse the existing XML file
            Document document = builder.parse(new File(path));
            document.getDocumentElement().normalize();

            // read current attribute 
            Element targetElement = (Element) document.getElementsByTagName(targetElementName).item(0);
            String currentAttributeName = targetElement.getAttribute("name");

            return currentAttributeName;
        } 
        catch (ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
        catch (SAXException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return "Element name not found!";
    }
    // updates the element name with the value of the string updateTo
    public void updateElementName(String targetElementName, String updateTo)
    {
        try 
        {
            // create a new DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // parse the existing XML file
            Document document = builder.parse(new java.io.File(path));

            // create a new Transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // set the output properties
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // create a new StreamResult to write to a file
            StreamResult result = new StreamResult(new java.io.File(path));

            // create a new DOMSource to read from the Document
            DOMSource source = new DOMSource(document);

            // read current attribute 
            Element targetElement = (Element) document.getElementsByTagName(targetElementName).item(0);
            String currentAttributeName = targetElement.getAttribute("name");

            // update attribute
            targetElement.removeAttribute("name");
            targetElement.setAttribute("name", updateTo); 
            String updatedAttributeName = targetElement.getAttribute("name");

            // save the updated XML file
            transformer.transform(source, result);

            System.out.println("The element with the name '" + targetElementName + "' has been updated from '" + currentAttributeName + "' to '" + updatedAttributeName + "'.");
        } 
        catch (ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
        catch (SAXException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}