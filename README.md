# InvoiceCollector
Also a hobby project. An application that can manage invoices (incomes and expenditures) of a household or a business.

Work in progress.

Endpoints for invoices:
GET
    /invoices - getting all existing invoices
    /invoice - get an invoice by ID
    /invoiceCod - getting invoices within a range of completion date 
    /invoiceCrd - getting invoices within a range of creation date 
    /invoiceDld - getting invoices within a range of deadline 
    /invoiceOd - getting all overdue invoices 
    /invoiceUp - getting all unpaid invoices
    /invoiceGTU - getting invoices by sum under a specific limit
    /invoiceGTO - getting invoices by sum over a specific limit
    /invoiceGTB - getting invoices by sum between specific limits
    /invoiceIn - getting all incoming invoices
    /invoiceOut - getting all outgoing invoices
    /invoicesPartner/{partner} - getting all invoices for a specific partner
    /invoicesPartner/{category} - getting all invoices for a specific category
POST
    /invoices - adding an invoice to the DB
PUT
    /invoices - updating any part of an existing invoice
    /invoices/update/cd - updating completion date for an invoice
    /invoices/update/cat - updating category for an invoice
DELETE
    /invoices - delete an invoice
