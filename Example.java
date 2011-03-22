import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.lulu.publish.client.PublishApiClient;
import com.lulu.publish.client.PublishApiException;
import com.lulu.publish.model.AccessType;
import com.lulu.publish.model.Author;
import com.lulu.publish.model.Bibliography;
import com.lulu.publish.model.BindingType;
import com.lulu.publish.model.Dimensions;
import com.lulu.publish.model.DistributionChannel;
import com.lulu.publish.model.FileDetails;
import com.lulu.publish.model.FileInfo;
import com.lulu.publish.model.JobFile;
import com.lulu.publish.model.JobManifest;
import com.lulu.publish.model.JobStatus;
import com.lulu.publish.model.PaperType;
import com.lulu.publish.model.PhysicalAttributes;
import com.lulu.publish.model.Pricing;
import com.lulu.publish.model.ProductType;
import com.lulu.publish.model.Project;
import com.lulu.publish.model.ProjectType;
import com.lulu.publish.model.TrimSize;
import com.lulu.publish.response.ErrorResponse;

public class Example {

    public static void main(String[] args) throws IOException, PublishApiException {
        Example example = new Example();
        example.publish();
    }

    public void publish() throws IOException, PublishApiException {
        PublishApiClient client = new PublishApiClient("/path/to/configuration.properties");

        client.login();

        File interiorFile = new File("/tmp/interior.pdf");
        File coverFile = new File("/tmp/cover.pdf");
        List<File> inputFiles = new ArrayList<File>();
        inputFiles.add(interiorFile);
        inputFiles.add(coverFile);
        client.upload(inputFiles);

        FileInfo fileInfo = new FileInfo();
        List<FileDetails> contents = new ArrayList<FileDetails>();
        FileDetails content = new FileDetails();
        content.setFilename(interiorFile.getName());
        content.setMimetype(FileDetails.MIME_TYPE_PDF);
        contents.add(content);
        fileInfo.setContents(contents);
        List<FileDetails> covers = new ArrayList<FileDetails>();
        FileDetails cover = new FileDetails();
        cover.setFilename(coverFile.getName());
        cover.setMimetype(FileDetails.MIME_TYPE_PDF);
        covers.add(cover);
        fileInfo.setCover(covers);
        
        Project project = createProjectStructure();
        project.setFileInfo(fileInfo);

        try {
            project = client.create(project);
            System.out.println("Created project with content id " + project.getContentId());
        } catch (PublishApiException e) {
            ErrorResponse error = client.getError();
            System.out.println(String.format("Got creation error (%s): %s", error.getErrorType(), error.getErrorValue()));
        }

        System.out.println("Your projects: ");
        for (Integer projectId : client.list()) {
            System.out.println(projectId);
        }

        // delta update
        Project updateProject = new Project();
        updateProject.setContentId(project.getContentId());

        Bibliography updateBibliography = new Bibliography();
        updateBibliography.setEdition("Third Edition");
        updateProject.setBibliography(updateBibliography);

        Pricing updatePrice = new Pricing();
        updatePrice.setCurrencyCode("GBP");
        updatePrice.setProductType(ProductType.PRINT);
        updatePrice.setTotalPrice(new BigDecimal(100.00));
        updatePrice.setDiscountPercent(50);
        List<Pricing> updatePrices = new ArrayList<Pricing>();
        updatePrices.add(updatePrice);
        updateProject.setPricingList(updatePrices);

        try {
            project = client.update(updateProject);
        } catch (PublishApiException e) {
            ErrorResponse error = client.getError();
            System.out.println(String.format("Got update error (%s): %s", error.getErrorType(), error.getErrorValue()));
        }

        try {
            client.delete(project.getContentId());
        } catch (PublishApiException e) {
            ErrorResponse error = client.getError();
            System.out.println(String.format("Got deletion error (%s): %s", error.getErrorType(), error.getErrorValue()));
        }
    }

    private Project createProjectStructure() {
        Project project = new Project();
        project.setProjectType(ProjectType.SOFTCOVER);
        project.setAllowRatings(false);
        Bibliography bibliography = new Bibliography();
        bibliography.setTitle("Test Paperback Book");
        List<Author> authors = new ArrayList<Author>();
        Author author = new Author();
        author.setFirstName("My");
        author.setLastName("Name");
        authors.add(author);
        bibliography.setAuthors(authors);
        bibliography.setCategory(1);
        bibliography.setDescription("This is a test book created through Lulu's publication API.");
        List<String> keywords = new ArrayList<String>() {
            {
                add("paperback");
                add("classic");
            }
        };
        bibliography.setKeywords(keywords);
        bibliography.setLicense("Public Domain");
        bibliography.setCopyrightYear(2010);
        bibliography.setCopyrightCitation("by Publishing Company");
        bibliography.setPublisher("Lulu.com");
        bibliography.setEdition("First");
        bibliography.setLanguage(Bibliography.LANGUAGE_ENGLISH);
        bibliography.setCountryCode(Bibliography.COUNTRY_USA);
        project.setBibliography(bibliography);
        PhysicalAttributes physicalAttributes = new PhysicalAttributes();
        physicalAttributes.setBindingType(BindingType.PERFECT);
        physicalAttributes.setTrimSize(TrimSize.US_LETTER);
        physicalAttributes.setPaperType(PaperType.REGULAR);
        physicalAttributes.setColor(false);
        project.setPhysicalAttributes(physicalAttributes);
        project.setAccessType(AccessType.PRIVATE);
        List<Pricing> pricingList = new ArrayList<Pricing>();
        Pricing download = new Pricing();
        download.setProductType(ProductType.DOWNLOAD);
        download.setCurrencyCode(Pricing.CURRENCY_CODE_USD);
        download.setTotalPrice(new BigDecimal(0.00));
        pricingList.add(download);
        Pricing print = new Pricing();
        print.setProductType(ProductType.PRINT);
        print.setCurrencyCode(Pricing.CURRENCY_CODE_USD);
        print.setTotalPrice(new BigDecimal(15.00));
        pricingList.add(print);
        project.setPricingList(pricingList);

        List<DistributionChannel> distribution = new ArrayList<DistributionChannel>();
        distribution.add(DistributionChannel.LULU_MARKETPLACE);
        project.setDistribution(distribution);
        return project;
    }

}

