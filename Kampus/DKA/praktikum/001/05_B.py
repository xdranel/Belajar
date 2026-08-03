def email_list(domains):
    emails = []
    for key, value in domains.items():
        for user in value:
            emails.append(user + "@" + key)

    return emails


email_domain = {
    "gmail.com": ["rafly.arj", "syahreza.adnan", "valen.hartanto"],
    "telkomuniversity.ac.id": ["sabrina.tiara", "imelda"],
    "yahoo.com": ["raihan.omen", "dito.asyraf"],
}

result = email_list(email_domain)

print(result)
