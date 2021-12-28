## Process Injection
# process-inject {
#
#     set allocator "{{ process_inject_allocator }}";
#     set min_alloc "{{ process_inject_min_alloc }}";
#     set startrwx "false";
#
#     set userwx   "false";
#
#     transform-x86 {
#         prepend "{{ process_inject_transform_x86_prepend }}";
#         append "{{ process_inject_transform_x86_append }}";
#     }
#
#     transform-x64 {
#         prepend "{{ process_inject_transform_x64_prepend }}";
#         append "{{ process_inject_transform_x64_append }}";
#     }
#
#     execute {
#         {{ process_inject_execute }}
#     }
# }

# dns-beacon “optional-variant-name” {
#    # Options moved into 'dns-beacon' group in 4.3:
#    set dns_idle              "1.2.3.4";
#    set dns_max_txt           "199";
#    set dns_sleep             "1";
#    set dns_ttl               "5";
#    set maxdns                "200";
#    set dns_stager_prepend    "doc-stg-prepend";
#    set dns_stager_subhost    "doc-stg-sh.";
#
#    # DNS subhost override options added in 4.3:
#    set beacon                "doc.bc.";
#    set get_A                 "doc.1a.";
#    set get_AAAA              "doc.4a.";
#    set get_TXT               "doc.tx.";
#    set put_metadata          "doc.md.";
#    set put_output            "doc.po.";
#    set ns_response           "zero";
# }
