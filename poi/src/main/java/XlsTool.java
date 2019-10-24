        import com.kpe.objects.Event;
        import com.kpe.objects.Location;
        import com.kpe.objects.VendorProduct;
        import com.kpe.objects.VendorSku;
        import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.ss.util.CellReference;

        import java.io.File;
        import java.io.IOException;
        import java.util.List;
        import java.util.concurrent.CopyOnWriteArrayList;

        import com.kpe.service.CodeService; public class XlsTool {
            private static final String FILE_NAME = "C:\\Users\\aaron\\Documents\\Kerry\\Correos Integration\\Códigos de países. Country codes.xlsx";
            private static final String PRD_FILE_NAME = "C:\\Users\\aaron\\Documents\\Kerry\\Correos Integration\\Códigos de productos. Product codes.xlsx";
            private static final String EVENTS_FILE ="C:\\Users\\aaron\\Documents\\Kerry\\Correos Integration\\events_code_and_reason_correos.xlsx";
            private static final String CONTENT_FILE="C:\\Users\\aaron\\Documents\\Kerry\\Correos Integration\\Códigos de contenido. Content codes.xlsx";
            public static void main(String[] args) {
        XlsTool tool = new XlsTool();
//        tool.importLocations();
//        tool.importVendorProducts();
        tool.importEvents(EVENTS_FILE);
//        tool.importContent(CONTENT_FILE);
    }
    public void importLocations(){ try {
        Workbook wb = WorkbookFactory.create(new File(FILE_NAME));
            Sheet sheet0 = wb.getSheetAt(0);

            List<Location> locationList = new CopyOnWriteArrayList<Location>();
            CodeService service = new CodeService(Location.class);
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet0) {

                if (row.getRowNum() == 0)

                    continue;
                Location location = null;
                location = new Location();
                for (Cell cell : row) {
                    int index = cell.getColumnIndex();

                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                    String text = dataFormatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0:
                           location.setVendorLocationName(text);
                            break;
                        case 1:
                            location.setCountryName(text);
                            break;
                        case 2:
                            location.setCountryCode(text);

                        default:
                            System.out.println();
                    }

                }
                //保存到数据库
                locationList.add(location);

            }
            service.saveList(locationList);
        } catch (IOException e) {
            System.out.println("解析文件遇到错误：" + FILE_NAME);
            e.printStackTrace();
        }
    }

    public void importVendorProducts(){

        try {
            Workbook wb = WorkbookFactory.create(new File(PRD_FILE_NAME));
            Sheet sheet0 = wb.getSheetAt(0);

            List<VendorProduct>  vendorProdList= new CopyOnWriteArrayList<VendorProduct>();
            CodeService service = new CodeService(VendorProduct.class);
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet0) {

                if (row.getRowNum() == 0)
                    continue;
                VendorProduct vendorProduct = null;
                vendorProduct= new VendorProduct();
                for (Cell cell : row) {
                    int index = cell.getColumnIndex();

                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                    String text = dataFormatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0:
                            vendorProduct.setProductCode(text);
                            break;
                        case 1:

                            vendorProduct.setNativeDesc(text);
                        case 2:
                            vendorProduct.setEnDesc(text);
                        default:
                            System.out.println();
                    }

                }
                //保存到数据库
                vendorProdList.add(vendorProduct);

            }
            service.saveList(vendorProdList);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
//Event Code	Event Description	Reason	Reason Description	Spanish Event & Reason Description
    private void importEvents(String filename){

        try {
            Workbook wb = WorkbookFactory.create(new File(filename));
            Sheet sheet0 = wb.getSheetAt(0);

            List<Event>  eventList = new CopyOnWriteArrayList<Event>();
            CodeService service = new CodeService(Event.class);
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet0) {

                if (row.getRowNum() == 0)
                    continue;
                Event event = new Event();
                for (Cell cell : row) {
                    int index = cell.getColumnIndex();

                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                    String text = dataFormatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0:
                            event.setCode(text);
                            break;

                        case 1:
                            event.setDescription(text);
                        case 2:
                            event.setReasonCode(text);
                        case 3:
                            event.setReasonDesc(text);
                        case 4:
                            event.setNativeDesc(text);
                        default:
                            System.out.println();
                    }

                }
                //保存到数据库
                eventList.add(event);
            }
            service.saveList(eventList);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private void importContent(String filename){
        try {
            Workbook wb = WorkbookFactory.create(new File(filename));
            Sheet sheet0 = wb.getSheetAt(0);

            List<VendorSku>  vendorSkuList= new CopyOnWriteArrayList<VendorSku>();
            CodeService service = new CodeService(VendorSku.class);
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet0) {

                if (row.getRowNum() == 0)
                    continue;
                VendorSku vendorSku = new VendorSku();
                for (Cell cell : row) {
                    int index = cell.getColumnIndex();

                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

                    String text = dataFormatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0:
                            vendorSku.setCode(text);
                            break;
                        case 1:
                            vendorSku.setVendorDesc(text);
                        case 2:
                            vendorSku.setDescription(text);
                        default:
                            System.out.println();
                    }

                }
                //保存到数据库
                vendorSkuList.add(vendorSku);
            }
            service.saveList(vendorSkuList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
