package helper.journal;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 6/23/14.
 */
public class DataManagment
{
    public static String WriteXMLJournalList(List<Journal> journals)
    {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        try
        {
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag("", "journals");
            for (Journal journal : journals)
            {
                xmlSerializer.startTag("", "journal");
                xmlSerializer.attribute("", "id", journal.id);
                xmlSerializer.attribute("", "name", journal.name);
                xmlSerializer.attribute("", "fileLocation", journal.fileLocation);
                xmlSerializer.endTag("", "journal");
            }
            xmlSerializer.endTag("", "journals");
            xmlSerializer.endDocument();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return stringWriter.toString();
        }
    }

    public static String WriteXMLEntryList(List<Entry> entries)
    {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        try
        {
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag("", "entries");
            for (Entry entry : entries)
            {
                xmlSerializer.startTag("", "entry");
                xmlSerializer.attribute("", "id", entry.id);
                xmlSerializer.attribute("", "date", entry.date);
                xmlSerializer.attribute("", "title", entry.title);
                xmlSerializer.attribute("", "fileLocation", entry.fileLocation);
                xmlSerializer.endTag("", "entry");
            }
            xmlSerializer.endTag("", "entries");
            xmlSerializer.endDocument();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return stringWriter.toString();
        }
    }

    public static String WriteXMLEntry(Entry entry)
    {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        try
        {
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag("", "entry");
            xmlSerializer.text(entry.text);
            xmlSerializer.endTag("", "entry");
            xmlSerializer.endDocument();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return stringWriter.toString();
        }
    }

    public static void SaveXMLDocuments(List<Journal> journals)
    {
        String journalXmls = WriteXMLJournalList(journals);
        List<String> entriesXmls = new ArrayList<String>();
        List<String> entryXmls = new ArrayList<String>();

        for (Journal journal : journals)
        {
            entriesXmls.add(WriteXMLEntryList(journal.entries));
            for (int i = 0; i < journal.entries.size(); i++)
            {
                entryXmls.add(WriteXMLEntry(journal.entries.get(i)));
            }
        }

        
    }
}
